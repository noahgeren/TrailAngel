package com.noahgeren.trailangel;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.noahgeren.trailangel.dao.ParkRepository;
import com.noahgeren.trailangel.dao.TrailRepository;
import com.noahgeren.trailangel.dao.UserRepository;
import com.noahgeren.trailangel.domain.Park;
import com.noahgeren.trailangel.domain.Trail;
import com.noahgeren.trailangel.domain.User;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	ParkRepository parkRepo;
	
	@Autowired
	TrailRepository trailRepo;

	@Override
	public void run(String... args) throws Exception {
		List<Park> parks = parkRepo.saveAll(Arrays.asList(
				new Park("Canyonlands"),
				new Park("Yellowstone"),
				new Park("Yosemite")
			));
		List<Trail> trails = trailRepo.saveAll(Arrays.asList(
				new Trail("Gooseberry Canyon", 1, 38.2136, 109.9025, 5.4, 5d, "Island in the Sky's steepest trail rapidly descends 1,400 feet (427 m) to the White Rim bench. Rough switchbacks cross sheer cliffs and scree slopes. Step carefully, and don't forget to look up to take in the view. Elevation change: 1,400 feet / 427 m"),
				new Trail("Murphy Point", 1, 38.2136, 109.9025, 3.6, 2d, "This longer hike leads past a historic corral on the mesa top. The trail ends with panoramic views of Candlestick Tower, the Green River, and the White Rim Road."),
				new Trail("Upheaval Dome", 1, 38.2136, 109.9025, 1d, 0.5, "A short but steep trail leads to a clear view into Upheaval Dome. Exhibits at the end of the trail discuss this unique geologic feature. One-hundred-foot elevation change (30 m). Hiking to second overlook adds 1 mile (1.5 km) and 50 feet (15 m) elevation change."),
				new Trail("White Rim Overlook", 1, 38.2136, 109.9025, 1.8, 1.5, "Walk to an east-facing overlook for views of the Colorado River, Monument Basin, and La Sal Mountains. Best in late afternoon. Very limited trailhead parking."),
				new Trail("Beaver Ponds", 2, 44.4280, 110.5885, 5d, 3d, "The trail gains over 350 feet in elevation and rambles through sagebrush meadows and stands of trees. The beaver ponds are approximately half-way around the loop. You might see beavers or their sign, but you are more likely to see muskrats and water birds. Along the way, look for bears, elk, mule deer, pronghorn, and moose."),
				new Trail("Bunsen Peak", 2, 44.4280, 110.5885, 4.6, 2.5, "Climb 1,300 feet through forest and meadow to the summit of Bunsen Peak, named for German chemist Robert Bunsen who studied geysers and invented the Bunsen Burner. The summit offers panoramic views of the Blacktail Deer Plateau, Swan Lake Flat, Gallatin Mountain Range, and the Yellowstone River Valley. Return by the same route."),
				new Trail("Lava Creek", 2, 44.4280, 110.5885, 4.2, 2.5, "Follow Lava Creek and the Gardner River. Along the way, you can see Undine Falls (60 feet / 18 m) and the confluence of Lava Creek with Gardner River. Near the west end of the trail, a footbridge crosses the Gardner River."),
				new Trail("Mammoth Hot Springs", 2, 44.4280, 110.5885, 1d, 1d, "For hundreds of years, Shoshone and Bannock people collected minerals from Mammoth Hot Springs for white paint. These minerals contribute to the beautiful terrace structures, along with heat, a natural “plumbing” system, water, and limestone. The volcanic heat source for Mammoth Hot Springs remains somewhat of a mystery. Scientists have proposed two sources, including the large magma chamber underlying the Yellowstone Caldera, or perhaps a smaller heat source closer to Mammoth."),
				new Trail("Bridalveil Fall", 3, 37.8651, 119.5383, 0.5, 0.33, "Plunging 620 feet (189 meters), Bridalveil Fall is often the first waterfall you'll see when entering Yosemite Valley. In spring, it thunders; during the rest of the year, look for its characteristic light, swaying flow. A paved trail leads from the parking area to the base of this waterfall, which flows year-round. Although paved, this is trail is not wheelchair accessible due to its grade."), 
				new Trail("Half Dome", 3, 37.8651, 119.5383, 15d, 12d, "Rising nearly 5,000 feet above Yosemite Valley and 8,800 feet above sea level, Half Dome is a Yosemite icon and a great challenge to many hikers. The most famous--or infamous--part of the hike is the ascent up the cables. The two metal cables allow hikers to climb the last 400 feet to the summit without rock climbing equipment."),
				new Trail("Lower Yosemite Fall", 3, 37.8651, 119.5383, 1d, 0.5, "Part of North America's tallest waterfall, Lower Yosemite Fall is the final 320-foot (98-meter) drop. Deafening in spring and early summer when the waterfall peaks in volume, you can expect to get sprayed with water when standing on the footbridge near its base."),
				new Trail("Yosemite Falls", 3, 37.8651, 119.5383, 7.2, 7d, "One of Yosemite's oldest historic trails (built 1873 to 1877), the Yosemite Falls Trail leads to the top of North America’s tallest waterfall, which rises 2,425 feet (739 m) above the Valley floor.")));
	}
}
