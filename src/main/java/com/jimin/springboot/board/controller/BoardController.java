package com.jimin.springboot.board.controller;


import com.jimin.springboot.board.mapper.BoardVO;
import com.jimin.springboot.board.service.BoardService;
import com.jimin.springboot.common.mapper.ResponseVO;
import com.jimin.springboot.security.service.SecurityService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class BoardController {

    private final SecurityService securityService;

    private final BoardService boardService;

    @Autowired
    public BoardController(SecurityService securityService, BoardService boardService) {
        this.securityService = securityService;
        this.boardService = boardService;
    }

    //전체 조회
    @GetMapping("/board/list")
    public String viewList(HttpServletRequest request)
    {
        return securityService.forwardLoginProtect(request, "board/board-list");
    }

    @PostMapping("/board/list")
    public List<BoardVO> boardlist(HttpServletRequest request)
    {
        List<BoardVO> boardList = new ArrayList<BoardVO>();

        return boardList;
    }

    //특정 글 하나만 조회
    @GetMapping("/board/one")
    public String viewOne(HttpServletRequest request)
    {
        return securityService.forwardLoginProtect(request, "board/board-view");
    }

    @ResponseBody
    @PostMapping("/board/one")
    public BoardVO boardOne(@RequestBody BoardVO boardVO)
    {
        BoardVO resultBoard = new BoardVO();

        return resultBoard;
    }


    //글 작성
    @GetMapping("/board/create")
    public String viewCreate(HttpServletRequest request)
    {
        return securityService.forwardLoginProtect(request, "board/board-create");
    }

    @ResponseBody
    @PostMapping("/board/create")
    public ResponseVO boardCreate(@RequestBody BoardVO boardVO)
    {
        ResponseVO resultMap = new ResponseVO();

        return resultMap;
    }

}
