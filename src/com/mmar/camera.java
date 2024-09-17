package com.mmar;
import java.io.File;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Parameters;
import java.io.IOException;
import java.io.FileOutputStream;
import android.graphics.SurfaceTexture;
import android.graphics.ImageFormat;

public class camera{
	public Camera mCamera;
	public int selfie_camera=1;
	public int back_camera=0;
	public int camera_id=0;
	public File imgfile=new File("/sdcard/cam.jpg");
	public camera(){
		
	}
	private void prepareCamera()
	{
		mCamera = Camera.open(camera_id);

		try
		{
			mCamera.setPreviewTexture(new SurfaceTexture(10));
		}
		catch (IOException e1)
		{

		}

		Parameters params = mCamera.getParameters();
		params.setPreviewSize(640, 480);
		params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
		params.setPictureFormat(ImageFormat.JPEG);
		mCamera.setParameters(params);
		mCamera.startPreview();
	}
	private void prepareCamera(int camera_id2)
	{
		mCamera = Camera.open(camera_id2);

		try
		{
			mCamera.setPreviewTexture(new SurfaceTexture(10));
		}
		catch (IOException e1)
		{

		}

		Parameters params = mCamera.getParameters();
		params.setPreviewSize(640, 480);
		params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
		params.setPictureFormat(ImageFormat.JPEG);
		mCamera.setParameters(params);
		mCamera.startPreview();
	}
	void takeshot()
	{

		mCamera.takePicture(null, null, null, new PictureCallback() {
				@Override
				public void onPictureTaken(byte[] data, Camera camera)
				{
					try
					{

						FileOutputStream outStream = new FileOutputStream(imgfile);    
						outStream.write(data);
						outStream.close();


					}
					catch (Exception e)
					{

					}


				}
			});

	}
	
	void takeshot(final File output)
	{

		mCamera.takePicture(null, null, null, new PictureCallback() {
				@Override
				public void onPictureTaken(byte[] data, Camera camera)
				{
					try
					{

						FileOutputStream outStream = new FileOutputStream(output);    
						outStream.write(data);
						outStream.close();


					}
					catch (Exception e)
					{

					}


				}
			});

	}
}
