package com.jfsoft.checker.controller;

import com.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 体检者
 */
@Controller
@RequestMapping("/checker")
public class CheckerController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

}
