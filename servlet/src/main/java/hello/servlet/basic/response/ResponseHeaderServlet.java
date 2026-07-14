package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // [status-line]
        response.setStatus(HttpServletResponse.SC_OK); // 일반적인 수를 넣는 것 보다, 내부적으로 정의된 상수를 쓰는게 좋다.

        // [response-headers]
        response.setHeader("Content-Type", "text/plane;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 캐시를 완전히 무효화 하겠다.
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello"); // 직접 정의한 헤더

        // [Header 편의 메서드]
//        content(response);
//        cookie(response);
//        redirect(response);

        // [message body]
        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }

    /**
     * 위 처럼 수동으로 넣는 대신 더 편리하게 header를 구성할 수 있는 편의 메서드를 제공한다.
     */

    // Content 편의 메서드
    private void content(HttpServletResponse response) {
        // Content-Type: text/plain;charset=utf-8
        // Content-Length: 2
        // response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        // response.setContentLength(2); // (생략시 자동 생성)
    }

    // 쿠키 편의 메서드
    private void cookie(HttpServletResponse response) {
        // Set-Cookie: myCookie=good; Max-Age=600;
        // response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); // 600초
        response.addCookie(cookie);
    }

    // redirect 편의 메서드
    private void redirect(HttpServletResponse response) throws IOException {
        // Status Code 302
        // Location: /basic/hello-form.html
        // response.setStatus(HttpServletResponse.SC_FOUND); // 302
        // response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}
