package com.br.marcelo.pessoas.app.factory.cargo;

import java.util.ArrayList;
import java.util.List;

import com.br.marcelo.pessoas.app.entity.cargo.Cargo;
import com.br.marcelo.pessoas.app.entity.cargo.Categoria;
import com.br.marcelo.pessoas.app.json.cargo.CargoJson;

public class CargoFactory {

	private static final CargoFactory INSTANCE = new CargoFactory();
	
	private CargoFactory() {
	}

	public static CargoFactory getInstance(){
		return INSTANCE;
	}

	public Cargo create(CargoJson json) {
		
		Cargo bean = new Cargo();
		
		if( json == null ){
			return bean;
		}
		
		bean.setId(json.getId());
		bean.setCodigo(json.getCodigo());
		bean.setNome(json.getNome());
		
		if( json.getCategoria() != null ){
			bean.setCategoria(new Categoria(json.getCategoria()));
		}
		
		if( json.getCargoPai() != null ){
			bean.setCargoPai(new Cargo(json.getCargoPai()));
		}
		
		return bean;
	}

	public List<CargoJson> create(List<Cargo> list) {
		
		List<CargoJson> nList = new ArrayList<CargoJson>();
		for(Cargo item : list){
			nList.add(create(item));
		}
		
		return nList;
	}

	public CargoJson create(Cargo item) {
		
		CargoJson json = new CargoJson();
		json.setNome(item.getNome());
		json.setCodigo(item.getCodigo());
		json.setId(item.getId());
		
		if( item.getCategoria() != null ){
			json.setCategoria(item.getCategoria().getId() );
			json.setNomeCategoria(item.getCategoria().getNome());
		}
		
		if( item.getCargoPai() != null ){
			json.setCargoPai(item.getCargoPai().getId() );
			json.setCargoPaiNome(item.getCargoPai().getNome());
			json.setCargoPaiCodigo(item.getCargoPai().getCodigo());
		}
		
		return json;
	}
}
