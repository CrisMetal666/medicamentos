package com.medicamentos.service;

import java.util.List;

/**
 * 
 * @author crismetal
 * 
 * Interface generica para las operaciones basicas para todas las capas de servicio
 *
 * @param <T> Modelo con el cual accederan a la base de datos
 */
public interface IService<T> {

	void guardar(T entidad) throws Exception;
	T buscarPorId(int id) throws Exception;
	void eliminar(int id) throws Exception;
	List<T> listarTodos() throws Exception;
}
