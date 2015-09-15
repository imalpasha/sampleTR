function filter(maxRangeMeters) {
//	alert(maxRangeMeters);
	// update culling distance, so only palces within given range are rendered
	AR.context.scene.cullingDistance = Math.max(maxRangeMeters, 1);

	// update radar's maxDistance so radius of radar is updated too
	PoiRadar.setMaxDistance(Math.max(maxRangeMeters, 1));
}