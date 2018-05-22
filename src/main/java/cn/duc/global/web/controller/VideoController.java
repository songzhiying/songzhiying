package cn.duc.global.web.controller;


import org.springframework.web.bind.annotation.*;

/**
 * @描述
 * @作者 szy
 * @创建时间 2018年5月21日
 * @版本 1.0
 */
@RestController
public class VideoController {

	/*
	 * 新增视频
 * @param category
 */
	@GetMapping("/video")
	public void addVideo() {
		System.out.println("Hello World.aa啊、" +
			"");
	}
}
