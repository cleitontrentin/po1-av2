package com.poo.uber.service;

import static spark.Spark.*;

import com.poo.uber.constant.CONFIGSERVICE;

public class ClientService {

	private static void init() {
		port(CONFIGSERVICE.PORT);
	}
	
	public static void main(String[] args) {
		init();

		get("/client/create/:json", (req, res) -> {
			res.status(200);
			return "Id usuario: "+req.params(":json");
			
		});
		
	}

}
