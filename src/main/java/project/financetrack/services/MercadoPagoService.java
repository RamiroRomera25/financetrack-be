package project.financetrack.services;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.stereotype.Service;

@Service
public interface MercadoPagoService {

    Preference createPreference(Long userId) throws MPException, MPApiException;

    String receiveNotification(String topic, String resource, Long userId) throws MPException, MPApiException;

}
