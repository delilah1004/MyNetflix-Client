package com.my.netflix.test.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.my.netflix.model.TVProgram;

@Component
public class TestAPIImp implements TestAPI {
	
	@Autowired
	AllService allService;
	
	// 콘텐츠 개수
    private static final int count = 12;

	@Override
	public ArrayList<TVProgram> getBestPopularTVPrograms() {

        return allService.getTVProgramList(getBestPopularTVIds());
    }

    // 인기 TV 프로그램 6개 Id 반환
    public ArrayList<Long> getBestPopularTVIds() {

        ArrayList<Long> allTvIdList = allService.getAllTVIds();
        ArrayList<Long> popularTvIdList = new ArrayList<Long>();

        for(int i=1; i<6; i++) {

            JsonArray results = allService.getPopularTVProgramIdList(i);

            for (JsonElement element : results) {
                long id = element.getAsJsonObject().get("id").getAsLong();

                if (allTvIdList.contains(id)) popularTvIdList.add(element.getAsJsonObject().get("id").getAsLong());

                if (popularTvIdList.size() == count) break;
            }

            if (popularTvIdList.size() == count) break;
        }

        return popularTvIdList;
    }
	
}
