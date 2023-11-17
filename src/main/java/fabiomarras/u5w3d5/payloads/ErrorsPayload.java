package fabiomarras.u5w3d5.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@Setter
@Getter
public class ErrorsPayload {
    private String message;
    private Date currentTime;
}
