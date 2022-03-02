package com.ty.stockadminister.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.ty.stockadminister.dao.OwnerDao;
import com.ty.stockadminister.dao.StaffDao;
import com.ty.stockadminister.dao.StockDao;
import com.ty.stockadminister.dao.SupplierDao;
import com.ty.stockadminister.dto.Owner;
import com.ty.stockadminister.dto.Staff;
import com.ty.stockadminister.dto.Stock;
import com.ty.stockadminister.dto.SupplierDto;
import com.ty.stockadminister.service.Stockservice;
import com.ty.stockadminister.util.ResponseStructure;

@Service
public class StockServiceImpl implements Stockservice {

	@Autowired
	StockDao dao;
	@Autowired
	OwnerDao ownerDao;
	@Autowired
	StaffDao staffDao;
	@Autowired
	SupplierDao supplierDao;
	

	@Override
	public ResponseEntity<ResponseStructure<Stock>> saveService(Stock stock,int userId,int supplierId) {
		Owner owner=ownerDao.getOwnerById(userId);
		Staff staff=null;
		if(owner==null) {
		 staff=staffDao.getStaffById(userId);
		}
		SupplierDto supplierDto=supplierDao.getbyid(supplierId);
		if((owner!=null)||(staff!=null)&&(supplierDto!=null)){
		stock.setOwner1(owner);
		stock.setStaff(staff);
		stock.setSupplier(supplierDto);
		ResponseStructure<Stock> structure = new ResponseStructure<Stock>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("success");
		structure.setData(dao.saveStock(stock));
		ResponseEntity<ResponseStructure<Stock>> entity = new ResponseEntity<ResponseStructure<Stock>>(structure,
				HttpStatus.OK);
		return entity;
		}else {
			ResponseStructure<Stock> responseStructure = new ResponseStructure<Stock>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("id not found");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<Stock>> entity = new ResponseEntity<ResponseStructure<Stock>>(
					responseStructure, HttpStatus.NOT_FOUND);
			return entity;
		}

	}

	@Override
	public ResponseEntity<ResponseStructure<List<Stock>>> getStock() {
		// TODO Auto-generated method stub
		ResponseStructure<List<Stock>> responseStructure = new ResponseStructure<List<Stock>>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("success");
		responseStructure.setData(dao.getStock());
		ResponseEntity<ResponseStructure<List<Stock>>> entity = new ResponseEntity<ResponseStructure<List<Stock>>>(
				responseStructure, HttpStatus.OK);
		return entity;

	}

	@Override
	public ResponseEntity<ResponseStructure<Stock>> updateStock(int id, Stock stock) {
		// TODO Auto-generated method stub
		Stock exist = dao.updateStock(id, stock);
		if (exist != null) {
			ResponseStructure<Stock> responseStructure = new ResponseStructure<Stock>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(dao.updateStock(id, stock));
			ResponseEntity<ResponseStructure<Stock>> entity = new ResponseEntity<ResponseStructure<Stock>>(
					responseStructure, HttpStatus.OK);
			return entity;
		} else {
			ResponseStructure<Stock> responseStructure = new ResponseStructure<Stock>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("not found");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<Stock>> entity = new ResponseEntity<ResponseStructure<Stock>>(
					responseStructure, HttpStatus.NOT_FOUND);
			return entity;
		}

	}

	@Override
	public ResponseEntity<ResponseStructure<Stock>> getStockById(int id) {
		// TODO Auto-generated method stub
		Stock stock = dao.getStockById(id);
		if (stock != null) {
			ResponseStructure<Stock> structure = new ResponseStructure<Stock>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("success");
			structure.setData(dao.getStockById(id));
			ResponseEntity<ResponseStructure<Stock>> entity = new ResponseEntity<ResponseStructure<Stock>>(structure,
					HttpStatus.OK);
			return entity;
		} else {
			ResponseStructure<Stock> responseStructure = new ResponseStructure<Stock>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("not found");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<Stock>> entity = new ResponseEntity<ResponseStructure<Stock>>(
					responseStructure, HttpStatus.NOT_FOUND);
			return entity;

		}
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> deleteStock(int id) {
		// TODO Auto-generated method stub
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		ResponseEntity<ResponseStructure<String>> entity = null;
		if (dao.deleteStock(id)) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData("deleted");
			entity = new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("id " + id + " not found");
			responseStructure.setData("not deleted");
			entity = new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
		}
		return entity;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Stock>>> getByProduct_Name(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Stock>>> getByReorder_Level(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Stock>>> getByLowReorderLevel() {
		List<Stock> list = dao.getByLowReorderLevel();
		if (list != null) {
			ResponseStructure<List<Stock>> responseStructure = new ResponseStructure<List<Stock>>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(dao.getByLowReorderLevel());
			ResponseEntity<ResponseStructure<List<Stock>>> entity = new ResponseEntity<ResponseStructure<List<Stock>>>(
					responseStructure, HttpStatus.OK);
			return entity;
		} else {
			ResponseStructure<List<Stock>> responseStructure = new ResponseStructure<List<Stock>>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("id not found");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<List<Stock>>> entity = new ResponseEntity<ResponseStructure<List<Stock>>>(
					responseStructure, HttpStatus.NOT_FOUND);
			return entity;

		}

	}

}
