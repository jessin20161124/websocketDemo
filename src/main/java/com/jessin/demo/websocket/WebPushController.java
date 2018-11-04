package com.jessin.demo.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * @author zexin.guo
 * @create 2018-06-28 上午10:44
 **/
@Controller
public class WebPushController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private MyWebSocketHandler myWebSocketHandler;

    @Resource
    private ServletContext servletContext;

    @PostConstruct
    public void init() {
        try {
            // /home/jessin/Documents/Program/Java/websocketDemo/src/main/webapp/hello
            logger.info("真实路径：{}", WebUtils.getRealPath(servletContext, "/hello"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * http://localhost:8080/push/sendToAllUser?message=abc
     *
     * @param message
     * @return
     * @throws UnsupportedEncodingException
     * @ResponseBody 不能返回String类型
     */
    @RequestMapping("/sendToAllUser")
    @ResponseBody
    public GeneralJsonResult<String> sendToAll(@RequestParam String message) throws UnsupportedEncodingException {
        logger.info("推送消息：{}", message);
        myWebSocketHandler.pushAllUser(message);
        return GeneralJsonResult.newSuccessResult("推送成功：" + message);
    }
}
