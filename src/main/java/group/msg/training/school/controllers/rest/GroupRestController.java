package group.msg.training.school.controllers.rest;


import group.msg.training.school.dtos.InboundGroup;
import group.msg.training.school.dtos.OutboundGroup;
import group.msg.training.school.services.BrowsingService;
import group.msg.training.school.services.StartOfYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rest/groups")
public class GroupRestController {
	private final StartOfYearService startOfYearService;
	private final BrowsingService browsingService;

	@PostMapping
	public OutboundGroup createEmptyGroup(@RequestBody InboundGroup inbound) {
		return startOfYearService.createEmptyGroup(inbound);
	}

	@GetMapping
	public List<OutboundGroup> getGroups() {
		return browsingService.listGroups();
	}
}
