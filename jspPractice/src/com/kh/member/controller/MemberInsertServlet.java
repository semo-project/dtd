package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberInserServlet
 */
@WebServlet("/insert.me")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 한글이 있을 경우 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		
		// 2. request에 있는 값 꺼내서 변수 또는 객체에 기록
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		String[] interests = request.getParameterValues("interest");
		
		// 배열은 매개변수로 전달 못해서 문자열로 만들기
		String interest = "";
		if(interests != null) {
			interest = String.join(",", interests);
		}
		
		// 전달값이 너무 많아 매개변수 생성자에 담아서 보내기
		Member mem = new Member(userId, userPwd, userName, phone, email, address, interest);
		
		// 3. 비즈니스 로직을 수행할 서비스 클래스의 메소드로 전달값 전달(호출)하고 그 결과값 받아주어야 함
		int result = new MemberService().insertMember(mem);
		
		// 4. 3의 결과값을 가지고 사용자가 보게 될 뷰 지정 후 포워딩 또는 리다이렉트 방식
		if(result > 0) {	// 회원가입 성공시
			
			HttpSession session = request.getSession();
			session.setAttribute("msg", "회원가입 성공");
			
			response.sendRedirect(request.getContextPath());
			
		} else {	// 회원가입 실패시
			
			request.setAttribute("msg", "회원가입 실패");
			
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
