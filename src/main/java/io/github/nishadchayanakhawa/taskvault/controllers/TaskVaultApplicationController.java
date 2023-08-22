package io.github.nishadchayanakhawa.taskvault.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskVaultApplicationController {
	@GetMapping("/taskCategory")
	public String getTaskCategoryManagemenePage() {
		return "taskCategoryManagement";
	}
}
