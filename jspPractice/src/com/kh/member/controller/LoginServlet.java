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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.me")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * < HttpServletRequest와 HttpServletResponse >
		 *
		 * - request : 서버로 요청하는 모든 정보들에 대해 보관하는 객체(즉, 요청시 전달되는 값들 담겨있음) - response : 요청에
		 * 응답할 때 필요한 객체
		 * 
		 * < Get방식과 Post방식 > - Get 방식 : URL의 Header에 데이터가 기록돼서 전달(url에 노출/데이터 길이제한
		 * 없음/즐겨찾기 가능) - Post 방식 : URL의 Body에 데이터가 기록돼서 전달(url에 노출안됌/데이터 길이제한 없음/즐겨찾기
		 * 불가능/전달값에 대해 한글 있을 시 인코딩 작업 필수)
		 * 
		 * 요청받았을 때의 정보 request에 담겨있다.
		 */
		
		// 1. 전달값에 한글 있을 시 인코딩 작업
		// request.setCharacterEncoding("UTF-8"); -> 로그인시 넘어오는 값 한글 없을거라 안해도된다.
		
		// 2. 전달값 꺼내서 변수 또는 객체에 기록하기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		// 3. 비즈니스 로직을 처리하는 서비스 클래스의 해당 메소드 호출 및 처리 결과 받기
		Member loginUser = new MemberService().loginMember(userId, userPwd);
		
		// 4. 처리 결과를 가지고 사용자가 보게 될 뷰 지정 후 포워딩 또는 리다이렉트 방식
		
		// 4_1. 응답 페이지에 전달할 값이 있을 경우 어딘가에 담아줘야 한다.(JSP 내장객체 종류)
		//	1) application : jsp, servlet, java 코드 다 접근해서 꺼내 쓸 수 있다.	--> 공유 범위가 제일 크다.
		//	2) session : 웹 브라우저당 하나씩 존재하는 객체 / jsp, servlet 다 접근해서 꺼내 쓸 수 있다. --> java는 안된다. 공유 범위가 application 다음으로 크다.
		//	3) request : 이 request 객체를 전달받은 응답 jsp에서만 꺼내 쓸 수 있다. 	--> 공유 범위 제한적
		//	4) page : 해당 jsp페이지 내에서만 접근해서 꺼내 쓸 수 있다.	--> 공유 범위가 제일 작다.
				
		// 위의 4 객체 모두 setAttribute(키, 밸류)를 이용해서 값 담기
		// 밸류값 꺼낼때는 getAttribute(키)를 이용하면 된다.
		// 삭제할 때는 removeAttribute(키)를 이용하면 된다.
				
		// 로그인 성공하면 세션객체에 회원 정보 담기(객체 생성해주어야함) -> 로그인 형태를 다른 페이지에서도 유지하려면 session 필요
		if(loginUser != null) {	// 해당 멤버 객체가 생성된 객체일 경우 == 조회 == 로그인 성공
			
			// request에 담은 객체는 그 해당 응답 페이지까지만 유효하고 다음 페이지부터는 사용할 수 없다. -> 하지만 로그인 이후에는 그 회원 객체를 어느 페이지든 사용할 수 있어야 한다.
			
			HttpSession session = request.getSession();	// 세션 객체에 담고자한다면 객체 생성해주어야 함 -> 이 곳은 servlet이기 때문, session은 jsp에서 바로 사용가능(request는 매개변수기 때문에 생성안해줘도됌)
			session.setAttribute("loginUser", loginUser);
			
			// sendRedirect 방식
			response.sendRedirect(request.getContextPath());	// /jsp -> 인덱스 페이지로 감
			
			
		} else {	// 해당 멤버 객체가 null == 조회x == 로그인 실패
			
			request.setAttribute("msg", "로그인 실패했습니다.");
			
			// RequestDispatcher : RequestDispatcher는 클라이언트로부터 최초에 들어온 요청을 JSP/Servlet 내에서 원하는 자원으로 요청을 넘기는(보내는) 역할을 수행하거나, 특정 자원에 처리를 요청하고 처리 결과를 얻어오는 기능을 수행하는 클래스
			// forward 방식
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
			
			// 응답 페이지를 지정하는 방식
			// -> forward 방식 : RequestDispatcher 객체 생성해서 사용자가 보게 될 뷰를 경로로서 지정(url에 찍히지 않음, 경로 안 찍힘), 내가 지정한 뷰가 단순히 사용자에게 보여줄 뿐이다.(즉, 서블릿 매핑값이 남아있음)
			// -> sendRedirect 방식 : forward와 같이 내가 지정한 뷰가 보여지지만 그 경로 자체가 url에 보여지는 방식(서블릿 매핑값이 남아있지 않음) 
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
