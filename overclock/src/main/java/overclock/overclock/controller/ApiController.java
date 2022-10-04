package overclock.overclock.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Lists;
import overclock.overclock.dto.*;
import overclock.overclock.entity.Comment;
import overclock.overclock.entity.Member;
import overclock.overclock.entity.Posts;
import overclock.overclock.model.search;
import overclock.overclock.model.subcard;
import overclock.overclock.service.CommentService;
import overclock.overclock.service.ItemService;
import overclock.overclock.service.MemberService;
import overclock.overclock.service.PostsService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@Log4j2
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
    private final PostsService postsService;
    private final MemberService memberService;

    private final ItemService itemService;
    private final CommentService commentService;

    @RequestMapping(value = "/memberRegister", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody MemberDTO dto){
        log.info("asd");
        log.info("api/memberRegister...:" + dto);
        String email = memberService.join(dto);
        return new ResponseEntity<>(email, HttpStatus.OK);
    }
    @RequestMapping(value = "/mregister", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> register(@RequestBody PostsDTO postsDTO){
        log.info("api/memberRegister...:" + postsDTO);
        Long id = postsService.mregister(postsDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @RequestMapping(value = "/mregister2", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> register2(@RequestBody ItemDTO itemDTO){
        log.info("api/memberRgister item DTO {}", itemDTO);
        Long id = itemService.mregister2(itemDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @RequestMapping(value = "/getlist", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResultDTO<PostsDTO, Posts>> getList(@RequestBody PageRequestDTO dto) {
        PageResultDTO<PostsDTO,Posts> result = postsService.getPageList(dto);
        log.info("postsDTO : {}", dto);
        log.info("List result : {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/partsList", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResultDTO<PostsDTO, Posts>> partsList(@RequestBody PageRequestDTO dto) {
        PageResultDTO<PostsDTO,Posts> result = postsService.partsCategoryPageList(dto);
        log.info("postsDTO : {}", dto);
        log.info("List result : {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/partsItemList", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDTO>> partsItemList(@RequestBody ItemDTO dto) {
        List<ItemDTO> result = itemService.partsItemList(dto);
        log.info("csacacsacsac : {}", dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    //소셜 로그인 후 회원정보수정
//    @RequestMapping(value = "/modify", method = RequestMethod.POST,
//            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> modify(@RequestBody MemberDTO memberDTO) {
//        String email = memberService.modify(memberDTO);
//        log.info("postsDTO : {}", memberDTO);
//        log.info("List result : {}", email);
//        return new ResponseEntity<>(email, HttpStatus.OK);
//    }

    
   //주변기기 디테일
    @RequestMapping(value = "/periList", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResultDTO<PostsDTO, Posts>> periList(@RequestBody PageRequestDTO dto) {
        PageResultDTO<PostsDTO,Posts> result = postsService.partsCategoryPageList(dto);
        log.info("postsDTO : {}", dto);
        log.info("List result : {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/mbDetail", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResultDTO<PostsDTO, Posts>> mbDetail(@RequestBody PageRequestDTO dto) {
        PageResultDTO<PostsDTO,Posts> result = postsService.getPageList(dto);
        log.info("postsDTO : {}", dto);
        log.info("List result : {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateView(@ModelAttribute("id") Long id) {
        log.info("asdasd : {}", id);
        PostsDTO postsDTO = postsService.updateView(id);

        return new ResponseEntity<>(postsDTO, HttpStatus.OK);
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> ArticleCardsSearch(@RequestBody search vo) {
        log.info("------------------------------------search--------------------");
        log.info(vo);
        return new ResponseEntity<>(postsService.getSearchList(vo), HttpStatus.OK);
    }

    // 댓글등록
    @RequestMapping(value = "/comment/add", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> comment(@RequestBody CommentDTO dto) {
        log.info("CommentDTO : {}", dto);
        Long commentDTO = commentService.addComment(dto);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/list", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResultDTO<CommentDTO, Comment>> commentList(@RequestBody PageRequestDTO dto) {
        PageResultDTO<CommentDTO,Comment> result = commentService.commentPageList(dto);
//        log.info("comment Id : {}", commentDTO);
        log.info("PageRequestDTO : {}", dto);
        log.info("Comment List result : {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/modify/check", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostsDTO> CheckarticleBeforeModify(@RequestBody subcard vo) {
        PostsDTO articleInfo = postsService.CheckBeforeModifyArticle(vo.getId(), vo.getUserid());
        return new ResponseEntity<>(articleInfo, HttpStatus.OK);
    }

    @RequestMapping(value = "/modify/send", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> articleModify(@RequestBody PostsDTO dto) {
        log.info("asasaas :" + dto);
        String articleInfo = postsService.PostsModify(dto);
        return new ResponseEntity<>(articleInfo, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> PostsDelete(@RequestBody PostsDTO dto) {
        log.info("ppppppppppppppppp :" + dto);
        Long articleInfo = postsService.PostsDelete(dto);
        return new ResponseEntity<>(articleInfo, HttpStatus.OK);
    }



}
