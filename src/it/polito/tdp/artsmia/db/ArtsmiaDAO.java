package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.ArtObjectIdMap;
import it.polito.tdp.artsmia.model.ExhibitionResult;

public class ArtsmiaDAO {

	public List<ArtObject> listObjects(ArtObjectIdMap artObjectIdMap) {
		
		String sql = "SELECT * from objects";
		List<ArtObject> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				ArtObject artObj = new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title"));
				
				result.add(artObjectIdMap.get(artObj));
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ExhibitionResult> getResult(ArtObjectIdMap artObjectIdMap) {
		
		String sql = "select e1.object_id as o1, e2.object_id as o2, count(*) as cnt " + 
				"from exhibition_objects as e1, exhibition_objects as e2 " + 
				"where e1.exhibition_id = e2.exhibition_id " + 
				"and e1.object_id < e2.object_id " + 
				"group by o1, o2";
		
		List<ExhibitionResult> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				
				ArtObject uno = artObjectIdMap.get(res.getInt("o1"));
				ArtObject due = artObjectIdMap.get(res.getInt("o2"));
				
				if(uno != null && due != null) {
					ExhibitionResult ex = new ExhibitionResult(uno.getId(), due.getId(),
							res.getInt("cnt"));
					
					result.add(ex);
				}
				
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
}
