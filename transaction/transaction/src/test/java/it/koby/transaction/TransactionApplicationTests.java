package it.koby.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionApplicationTests {

	@Test
	    public void testGetTransactionById_whenTransactionExists_shouldReturnTransaction() {
        String id = "1";
        Transaction transaction = new Transaction();
        when(transactionRepository.findById(id)).thenReturn(Optional.of(transaction));

        Transaction result = transactionService.getTransactionById(id);

        verify(transactionRepository, times(1)).findById(id);
        assert result == transaction;
    }

}
