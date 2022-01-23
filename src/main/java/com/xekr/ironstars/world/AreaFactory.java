package com.xekr.ironstars.world;

public interface AreaFactory<A extends Area> {
	A make();
}