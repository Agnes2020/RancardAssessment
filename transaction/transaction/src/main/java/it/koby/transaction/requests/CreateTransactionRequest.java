package it.koby.transaction.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CreateTransactionRequest{
    @NotBlank
    @Pattern(regexp = "^\\S+\\s+\\S+$",
             message = "Sender name must be full name.",
             flags = Pattern.Flag.CASE_INSENSITIVE)
    @Schema(description = "Sender name", type = "string", example = "Sender Name")
    private String senderName;

    @NotBlank 
    @Pattern(regexp = "^\\S+\\s+\\S+$", 
             message = "Receiver name must be full name.",
             flags = Pattern.Flag.CASE_INSENSITIVE)
             @Schema(description = "Receiver name", type = "string", example = "Receiver Name")
    private String receiverName;
 
    @PositiveOrZero(message = "Amount to send must start from 0 and above.")
    private Double amountToSend;
}
