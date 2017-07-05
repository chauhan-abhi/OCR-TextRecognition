package com.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Api
 */
@WebServlet("/Api")
public class Api extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// PostImage postimage;
	String url = "https://api.ocr.space/parse/image";

	/**
	 * Default constructor.
	 */
	public Api() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String API_KEY = "7175c8088b88957";
		boolean isOverlayRequired = false;
		String imageUrl = request.getParameter("imageUrl");
		System.out.println(imageUrl);
		String language = request.getParameter("ocrLanguage");
		String result = "";
		PostImage post = new PostImage();
		try {
			System.out.println("sending request----");
			result = post.sendPost(API_KEY, isOverlayRequired, imageUrl, language);
			// out.println(result);
		} catch (Exception e) {
			out.print("No response");
			e.printStackTrace();
		}
		try {
			System.out.println("parsing response");
			String r=post.parseJSON(result);
			out.println(r);
			out.println(post.blacklist(r));
		} catch (Exception e) {
			out.print("parsing error");
		}

	}

}
