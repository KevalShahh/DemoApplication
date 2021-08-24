package com.example.demoapplication

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.SurfaceTexture
import android.hardware.camera2.*
import android.hardware.camera2.CameraCaptureSession.CaptureCallback
import android.media.Image
import android.media.ImageReader
import android.media.ImageReader.OnImageAvailableListener
import android.os.Bundle
import android.os.Environment
import android.util.Size
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.Surface
import android.view.TextureView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.demoapplication.databinding.ActivityCamera2ApiBinding
import kotlinx.android.synthetic.main.activity_camera2_api.*
import java.io.*
import java.util.ArrayList


class Camera2Api : AppCompatActivity() {
    lateinit var viewBinding: ActivityCamera2ApiBinding

    companion object {
        val orientations = SparseIntArray()
        var cam_front = "1"
        var cam_back = "0"

        init {
            orientations.append(Surface.ROTATION_0, 90)
            orientations.append(Surface.ROTATION_90, 0)
            orientations.append(Surface.ROTATION_180, 270)
            orientations.append(Surface.ROTATION_270, 180)
        }
    }

    private var cameraId = cam_back
    var cameraDevice: CameraDevice? = null
    var cameraCaptureSessions: CameraCaptureSession? = null
    var captureRequestBuilder: CaptureRequest.Builder? = null
    var imageDimension: Size? = null
    private var imageReader: ImageReader? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCamera2ApiBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.textureView.surfaceTextureListener = textureListener
        viewBinding.btnTakePicture.setOnClickListener {
            takePicture()
        }
        viewBinding.btnFlipCam.setOnClickListener {
            flipCamera()
        }
    }

    private fun flipCamera() {
        if (cameraId == cam_front) {
            cameraId = cam_back
            closeCamera()
            openCamera()
        } else if (cameraId == cam_back) {
            cameraId = cam_front
            closeCamera()
            openCamera()
        }
    }

    private fun closeCamera() {
        if (null != cameraDevice) {
            cameraDevice!!.close()
            cameraDevice = null
        }
        if (null != imageReader) {
            imageReader!!.close()
            imageReader = null
        }
    }

    private fun takePicture() {
        val cameraManager: CameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        val characteristics = cameraDevice?.let { cameraManager.getCameraCharacteristics(it.id) }
        val jpegSizes: Array<Size>? =
            characteristics?.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)!!
                .getOutputSizes(ImageFormat.JPEG)
        var width = 640
        var height = 480
        if (jpegSizes != null && jpegSizes.isNotEmpty()) {
            width = jpegSizes[0].width
            height = jpegSizes[0].height
        }
        val reader = ImageReader.newInstance(width, height, ImageFormat.JPEG, 1)

        val outputSurfaces: MutableList<Surface> = ArrayList(2)
        outputSurfaces.add(reader.surface)
        outputSurfaces.add(Surface(viewBinding.textureView.surfaceTexture))

        val captureBuilder = cameraDevice?.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE)
        captureBuilder?.addTarget(reader.surface)
        captureBuilder?.set(CaptureRequest.CONTROL_MODE, CaptureRequest.CONTROL_MODE_AUTO)
        val rotation = windowManager.defaultDisplay.rotation
        captureBuilder?.set(
            CaptureRequest.JPEG_ORIENTATION,
            orientations[rotation]
        )
        val epoch = System.currentTimeMillis() / 1000

        val folderArray: Array<File> = getExternalFilesDirs(Environment.DIRECTORY_PICTURES)
        val folder: File = folderArray[1]
        val file = File(folder, "$epoch" + "pic.png")
        val readerListener: OnImageAvailableListener = object : OnImageAvailableListener {
            override fun onImageAvailable(reader: ImageReader) {
                val image: Image? = reader.acquireLatestImage()
                val buffer = image?.planes!![0].buffer
                val bytes = ByteArray(buffer.capacity())
                buffer[bytes]
                save(bytes)
            }

            private fun save(bytes: ByteArray) {
                val output: OutputStream?
                output = FileOutputStream(file)
                output.write(bytes)
                val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                viewBinding.imgView.setImageBitmap(
                    Bitmap.createScaledBitmap(
                        bmp,
                        viewBinding.imgView.width,
                        viewBinding.imgView.height,
                        false
                    )
                )
                Toast.makeText(this@Camera2Api, "img displayed", Toast.LENGTH_SHORT).show()
            }
        }
        reader.setOnImageAvailableListener(readerListener, null)
        //
        cameraDevice!!.createCaptureSession(
            outputSurfaces,
            object : CameraCaptureSession.StateCallback() {
                override fun onConfigured(session: CameraCaptureSession) {
                    val captureCallback: CaptureCallback = object : CaptureCallback() {
                        override fun onCaptureCompleted(
                            session: CameraCaptureSession,
                            request: CaptureRequest,
                            result: TotalCaptureResult
                        ) {
                            super.onCaptureCompleted(session, request, result)
                            Toast.makeText(this@Camera2Api, "Saved:$file", Toast.LENGTH_LONG)
                                .show()
                            createCameraPreview()
                        }
                    }
                    session.capture(captureBuilder?.build()!!, captureCallback, null)
                    Toast.makeText(this@Camera2Api, "captured successfully", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onConfigureFailed(session: CameraCaptureSession) {

                }
            },
            null
        )
    }

    private var textureListener: TextureView.SurfaceTextureListener =
        object : TextureView.SurfaceTextureListener {
            override fun onSurfaceTextureAvailable(
                surface: SurfaceTexture,
                width: Int,
                height: Int
            ) {
                openCamera()
            }

            override fun onSurfaceTextureSizeChanged(
                surface: SurfaceTexture,
                width: Int,
                height: Int
            ) {

            }

            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
                return false
            }

            override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {

            }

        }

    private fun openCamera() {
        val cameraManager: CameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 200)
            return
        }
        cameraManager.openCamera(cameraId, stateCallBack, null)
    }

    private val stateCallBack: CameraDevice.StateCallback = object : CameraDevice.StateCallback() {
        override fun onOpened(camera: CameraDevice) {
            cameraDevice = camera
            createCameraPreview()
        }

        override fun onDisconnected(camera: CameraDevice) {
            cameraDevice?.close()
        }

        override fun onError(camera: CameraDevice, error: Int) {
            cameraDevice?.close()
            cameraDevice = null
        }

    }

    private fun createCameraPreview() {
        val texture = viewBinding.textureView.surfaceTexture
        val surface = Surface(texture)
        captureRequestBuilder = cameraDevice?.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
        captureRequestBuilder?.addTarget(surface)
        cameraDevice?.createCaptureSession(
            listOf(surface),
            object : CameraCaptureSession.StateCallback() {
                override fun onConfigured(session: CameraCaptureSession) {
                    cameraCaptureSessions = session
                    updatePreview()
                }

                override fun onConfigureFailed(session: CameraCaptureSession) {

                }

            },
            null
        )
    }

    private fun updatePreview() {
        captureRequestBuilder?.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO)
        cameraCaptureSessions?.setRepeatingRequest(captureRequestBuilder?.build()!!, null, null)
    }
}
