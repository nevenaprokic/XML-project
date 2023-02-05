package rs.ac.uns.ftn.services;

import java.awt.image.BufferedImage;

public interface QRCodeService {
	BufferedImage generateQRCodeImage(String content) throws Exception;
	String generateQRCodeBase64String(String content) throws Exception;
}
