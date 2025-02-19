package es.rdajila.apipeliculas.api.service;

import lib.rdajila.helper.ResponseHelper;

public interface ICrticaApiService {
    ResponseHelper deleteByPeliculaId(int ePeliculaId);
}
