package rs.ac.uns.ftn.services.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import rs.ac.uns.ftn.services.QRCodeService;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

@Service
public class QRCodeServiceImpl implements QRCodeService {

	public BufferedImage generateQRCodeImage(String content) throws Exception {
		QRCodeWriter barcodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = barcodeWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200);
		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}

	public String generateQRCodeBase64String(String content) throws Exception {
		BufferedImage image = generateQRCodeImage(content);
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "png", os);
		return "data:image/png;base64," + Base64.getEncoder().encodeToString(os.toByteArray());
	}
	
}
