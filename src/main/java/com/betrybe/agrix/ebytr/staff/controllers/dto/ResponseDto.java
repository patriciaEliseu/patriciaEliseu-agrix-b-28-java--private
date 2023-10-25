package com.betrybe.agrix.ebytr.staff.controllers.dto;

/**
 * record.
 */


public record ResponseDto<T>(String message, T data) {
}
