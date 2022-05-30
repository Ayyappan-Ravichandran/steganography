package com.steganography.controller;

import javax.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.steganography.service.DecodeService;
import com.steganography.service.EncodeService;

@MultipartConfig
@Controller
public class SteganographyController {

	@Autowired
	EncodeService encode;

	@Autowired
	DecodeService decode;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String WelcomPage(ModelMap model) {
		return "home";
	}

	@RequestMapping(value = "/encode", method = RequestMethod.POST)
	public String Encode(ModelMap model, @RequestParam MultipartFile image, @RequestParam String message,
			@RequestParam String key) throws Exception {
		
		System.out.println("inside Encode");

		Boolean encodevalue = encode.SteganoEncode(image, message, key);

		if (encodevalue) {
			model.put("message", "Encription Success");
		} else {
			model.put("errorMessage", "Encription Failure");
		}
		return "home";
	}

	@RequestMapping(value = "/decode", method = RequestMethod.POST)
	public String Decode(ModelMap model, @RequestParam MultipartFile image, @RequestParam String key) throws Exception {
		
		System.out.println("inside Decode");

		Boolean decodevalue = decode.SteganoDecode(image, key);

		if (decodevalue) {
			model.put("message", "Decription Success");
		} else {
			model.put("errorMessage", "Decription Failure");
		}
		return "home";
	}

}
