package org.option.service.rest;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DomainFacade {
	
	private static DomainFacade instance = new DomainFacade();
	
	private DomainFacade(){
		
	}
	public Document getDocument(String url){
		try {
			System.out.println("HTTP request :" + url);
			Document doc = Jsoup.connect(url).get();
			return doc;
		} catch (IOException e) {
			
			e.printStackTrace();
		}  
		return null;
	}

	public Document getDocumentFromFile(String filePath){
		try {
			Document doc = Jsoup.parse(new File(filePath),"utf-8");
			return doc;
		} catch (IOException e) {
			
			e.printStackTrace();
		}  
		return null;
	}


	public static DomainFacade getInstance() {
		return instance;
	}

	
}
