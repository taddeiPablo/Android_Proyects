package com.taddeipablo.reconocimiento_imagenes

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.SurfaceTexture
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Surface
import android.view.TextureView
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import androidx.core.content.getSystemService
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.taddeipablo.reconocimiento_imagenes.ml.AutoModel2
import org.tensorflow.lite.support.common.FileUtil
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp

/**
 * Conclusión
 * Este código toma imágenes en tiempo real de la cámara, ejecuta un modelo de IA para detectar objetos y muestra los resultados sobre la imagen.
 * Si necesitas profundizar en alguna parte en particular, dime y lo analizamos más a fondo.
 * */

// CLASE MainActivity - CLASE PRINCIPAL ENLAZADA CON LA VIEW Y PUNTO PRINCIPAL
// DE ENTRADA EN LA APP.
class MainActivity : AppCompatActivity() {
    // aqui declaro las variables utilizadas en la app
    lateinit var imageProcessor: ImageProcessor // Se usa para preprocesar imágenes antes de pasarlas al modelo de IA.
    lateinit var cameraDevice: CameraDevice // Maneja la cámara del dispositivo.
    lateinit var handler: Handler // Se usa para ejecutar tareas en un hilo secundario.
    lateinit var cameraManager: CameraManager // Administra las cámaras del dispositivo.
    lateinit var textureView: TextureView // Donde se muestra la imagen de la cámara en tiempo real.
    lateinit var imageview: ImageView // Donde se mostrarán los resultados con anotaciones.
    lateinit var bitmap: Bitmap // Para almacenar la imagen capturada de la TextureView.
    lateinit var model: AutoModel2 // Un modelo de IA (AutoModel2) para detección de objetos.
    val paint = Paint() // Se usa para dibujar sobre la imagen detectada.
    lateinit var labels: List<String> // Contiene los nombres de los objetos detectados.
    // Además, hay una lista de colores que se usa para diferenciar las categorías detectadas
    var colors = listOf<Int>(
        Color.BLUE, Color.GREEN, Color.RED, Color.CYAN, Color.GRAY, Color.BLACK, Color.DKGRAY, Color.MAGENTA, Color.YELLOW, Color.RED
    )
    lateinit var btnFilter: Button

    // Este es el punto de entrada principal de la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Probablemente se usa para el modo de pantalla completa.
        setContentView(R.layout.activity_main) // Establece el layout de la actividad.
        get_permission() // Pide permisos para la cámara.

        // Carga los nombres de los objetos detectables (labels) desde un archivo.
        labels = FileUtil.loadLabels(this, "mobilenet_objectdetection_labels.txt")
        // Crea un imageProcessor que ajusta las imágenes a 300x300 píxeles para el modelo.
        imageProcessor = ImageProcessor.Builder().add(ResizeOp(300,300,ResizeOp.ResizeMethod.BILINEAR)).build()
        // Carga el modelo AutoModel2 de detección de objetos.
        model = AutoModel2.newInstance(this)

        // ------------------------------------- //
        // Inicialización de Cámara y Superficie //
        // ------------------------------------- //
        // Crea un HandlerThread llamado "video" para manejar las tareas de la cámara en un hilo separado.
        val handlerThread = HandlerThread("video")
        handlerThread.start()
        handler = Handler(handlerThread.looper)
        // Obtiene referencias a la TextureView y a la ImageView en la UI.
        textureView = findViewById(R.id.textureV)
        imageview = findViewById(R.id.imageV)

        btnFilter = findViewById(R.id.filter)

        btnFilter.setOnClickListener {
            showPopup()
        }

        //Cuando TextureView está lista (onSurfaceTextureAvailable), se llama open_camera().
        //onSurfaceTextureUpdated se ejecuta cada vez que la cámara actualiza la imagen.
        textureView.surfaceTextureListener = object : TextureView.SurfaceTextureListener {
            override fun onSurfaceTextureAvailable(
                surface: SurfaceTexture,
                width: Int,
                height: Int
            ) {
                open_camera()
            }

            override fun onSurfaceTextureSizeChanged(
                surface: SurfaceTexture,
                width: Int,
                height: Int
            ) {
                TODO("Not yet implemented")
            }

            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
                return false;
            }
            // se ejecuta cada vez que la cámara actualiza la imagen
            // Dentro de onSurfaceTextureUpdated, ocurre la detección
            override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {
                Log.d("Camera", "SurfaceTexture actualizado")
                bitmap = textureView.bitmap!!

                // Creates inputs for reference.
                // Convierte la imagen capturada (bitmap) en un objeto TensorImages
                var image = TensorImage.fromBitmap(bitmap)
                // La procesa con imageProcessor para adaptarla al modelo.
                image = imageProcessor.process(image)


                // Ejecución del Modelo de IA

                // Ejecuta el modelo en la imagen procesada.v
                // Runs model inference and gets result.
                val outputs = model.process(image)
                // Obtiene el primer resultado de detección.
                val detectionResult = outputs.detectionResultList.get(0)

                // Obtención de Datos del Modelo
                // Gets result from DetectionResult.
                //  Coordenadas de los objetos detectados en la imagen.
                val locations = outputs.locationAsTensorBuffer.floatArray //detectionResult.locationAsRectF;
                // Índices de las categorías de los objetos detectados.
                val categories = outputs.categoryAsTensorBuffer.floatArray //detectionResult.categoryAsString;
                // Confianza de cada detección.
                val scores = outputs.scoreAsTensorBuffer.floatArray //detectionResult.scoreAsFloat;

                // Dibujo de las Detecciones en la Imagen
                // Crea una copia de la imagen en un Bitmap mutable.
                var mutable = bitmap.copy(Bitmap.Config.ARGB_8888, true)
                // Crea un Canvas para dibujar sobre la imagen.
                val canvas = Canvas(mutable)

                val h = mutable.height
                val w = mutable.width

                // Dibuja un rectángulo en la imagen sobre los objetos detectados.
                // Dibuja el nombre del objeto detectado y la confianza del modelo sobre la imagen.
                paint.textSize = h/15f
                paint.strokeWidth = h/85f
                var x = 0
                scores.forEachIndexed { index, fl ->
                    x = index
                    x *=4
                    if(fl > 0.5){
                        paint.setColor(colors.get(index))
                        paint.style = Paint.Style.STROKE
                        canvas.drawRect(RectF(locations.get(x+1)*w, locations.get(x)*h, locations.get(x+3)*w, locations.get(x+2)*h), paint)
                        paint.style = Paint.Style.FILL
                        canvas.drawText(labels.get(categories.get(index).toInt())+" "+fl.toString(),locations.get(x+1)*w , locations.get(x)*h, paint)
                    }
                }
                // Muestra la imagen procesada con anotaciones en imageview
                imageview.setImageBitmap(mutable)
            }
        }

        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        model.close()
    }
    // METODO PIDIENDO PERMISO PARA UTILIZAR LA CAMARA
    fun get_permission(){
        // Solicita permisos para acceder a la cámara.
        // Si el permiso fue denegado, lo vuelve a pedir.
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            //requestPermissions(arrayOf(android.Manifest.permission.CAMERA), 101)
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 101)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults,deviceId)
        if(grantResults[0] != PackageManager.PERMISSION_GRANTED){
            get_permission()
        }
    }
    // Apertura de la Cámara
    @SuppressLint("MissingPermission")
    fun open_camera(){
        // Abre la cámara usando cameraManager.openCamera().
        cameraManager.openCamera(cameraManager.cameraIdList[0], object: CameraDevice.StateCallback(){
            override fun onOpened(camera: CameraDevice) {
                cameraDevice = camera

                // Obtiene la textura de la TextureView y la asocia a una Surface.
                var surfaceTexture = textureView.surfaceTexture
                var surface = Surface(surfaceTexture)

                // Configura la cámara para capturar imágenes y enviarlas a la Surface
                var captureRequest = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
                captureRequest.addTarget(surface)

                cameraDevice.createCaptureSession(listOf(surface), object:CameraCaptureSession.StateCallback(){
                    override fun onConfigured(session: CameraCaptureSession) {
                        session.setRepeatingRequest(captureRequest.build(), null, null)
                    }

                    override fun onConfigureFailed(session: CameraCaptureSession) {
                        TODO("Not yet implemented")
                    }

                },handler)

            }

            override fun onDisconnected(camera: CameraDevice) {
                TODO("Not yet implemented")
            }

            override fun onError(camera: CameraDevice, error: Int) {
                TODO("Not yet implemented")
            }

        }, handler)
    }

    // Desde aqui se abre el popup
    fun showPopup(){
        // aqui preparo un inflater para luego inflar el layout requerido
        val inflater = LayoutInflater.from(this)
        // aqui inflamos el layout que mostraremos como popup
        val popupView = inflater.inflate(R.layout.popup_layout, null)
        // aqui finalmente creo el popup configurando algunos parametros
        // como por ejemplo el espacio que va a ocupara y demas valores
        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )
        // aqui determino en que parte de la pantalla se va a mostrar el popup
        popupWindow.showAtLocation(textureView, Gravity.CENTER, 0, 0)

        // aqui tomo el boton que tiene el layout del popup para luego cerrar la ventana
        val closeButton = popupView.findViewById<Button>(R.id.close_popup)
        // utilizo un evento onclick para cerrar la ventana.
        closeButton.setOnClickListener{
            popupWindow.dismiss()
        }
    }
}
