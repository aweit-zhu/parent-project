package com.aweit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YamlLabController {

	@Value("${yamllab.case1}")	//	'abc/\n'
	private String yamllabCase1;

	@Value("${yamllab.case2}")	//	"abc/\n"
	private String yamllabCase2;

	@Value("${yamllab.case3}")	//	abc/\n
	private String yamllabCase3;

	@Value("${yamllab.case4}")	//	123
	private String yamllabCase4;

	@Value("${yamllab.case5}")	//	'123'
	private String yamllabCase5;

	@Value("${yamllab.case6}")	//	empty
	private String yamllabCase6;

	@Value("${yamllab.case7}")	//	{}:[],&*#?|-<>=!%\@
	private String yamllabCase7;

	@GetMapping(value = "/yamllab")
	public ResponseEntity<String> getExampleProperty() {
		StringBuilder sb = new StringBuilder();
		String result = sb
				.append(yamllabCase1).append(" | ")
				.append(yamllabCase2).append(" | ")
				.append(yamllabCase3).append(" | ")
				.append(yamllabCase4).append(" | ")
				.append(yamllabCase5).append(" | ")
				.append(yamllabCase6).append(" | ")
				.append(yamllabCase7)
				.toString();
		return ResponseEntity.ok(result);
	}

}
