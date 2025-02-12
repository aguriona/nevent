package com.nevent.challenge.service;

import com.nevent.challenge.dto.OrderRequest;
import com.nevent.challenge.dto.OrderResponse;
import com.nevent.challenge.entity.Status;

import java.util.List;

import com.nevent.challenge.exception.OrderNotFoundException;
import org.springframework.data.domain.Page;

/**
 * Servicio para gestionar operaciones relacionadas con órdenes.
 */
public interface OrderService {

    /**
     * Crea una nueva orden.
     *
     * @param request la solicitud con los datos de la orden.
     * @return la respuesta con los detalles de la orden creada.
     */
    OrderResponse createOrder(OrderRequest request);

    /**
     * Obtiene una orden por su ID.
     *
     * @param id el identificador de la orden.
     * @return la orden correspondiente.
     * @throws OrderNotFoundException si la orden no existe.
     */
    OrderResponse getOrderById(String id);

    /**
     * Obtiene todas las órdenes con paginación.
     *
     * @param page número de página.
     * @param size cantidad de elementos por página.
     * @return una página de órdenes.
     */
    Page<OrderResponse> getAllOrders(int page, int size);

    /**
     * Obtiene una lista de órdenes filtradas por estado.
     *
     * @param status el estado de las órdenes a filtrar.
     * @return una lista de órdenes con el estado especificado.
     */
    List<OrderResponse> getOrdersByStatus(String status);

    /**
     * Actualiza el estado de una orden.
     *
     * @param id el identificador de la orden.
     * @param status el nuevo estado de la orden.
     * @return la orden actualizada.
     * @throws OrderNotFoundException si la orden no existe.
     */
    OrderResponse updateOrderStatus(String id, Status status);

    /**
     * Elimina una orden por su ID.
     *
     * @param id el identificador de la orden.
     * @throws OrderNotFoundException si la orden no existe.
     */
    void deleteOrder(String id);
}
