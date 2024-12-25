package es.rdajila.apipeliculas.api.service;

import lib.rdajila.helper.ResponseHelper;

public interface IRolApiService {
    ResponseHelper getByCode(String eCode);
}