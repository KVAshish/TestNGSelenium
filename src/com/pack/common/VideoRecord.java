package com.pack.common;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import com.pack.common.TestRecorder;
import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;




import org.monte.media.math.Rational;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.*;

public class VideoRecord {

	
	private static TestRecorder _specializedTestRecorder;
	//private static TestVideoRecorder testVideoRecorder;
	

	/*
	 * Method is used for starting the recording.
	 * 
	 * 
	 */

	public void startRecording(String testCaseName) throws Exception

	{
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		Rectangle captureSize = new Rectangle(0, 0, width, height);
		File movieFolder = new File("/home/ruchi/Videos" + File.separator);

		_specializedTestRecorder = new TestRecorder(gc, captureSize,
				new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
						CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
						Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
				null, movieFolder, testCaseName);

		_specializedTestRecorder.start();
	}

	public void stopRecording() throws Exception {
		//this.screenRecorder.stop();
		try {

//            if(testVideoRecorder != null)
//                testVideoRecorder.stopRecording();

            if (_specializedTestRecorder != null)
                _specializedTestRecorder.stop();

        } catch (Throwable t) {
            t.printStackTrace();
        }

	}

}