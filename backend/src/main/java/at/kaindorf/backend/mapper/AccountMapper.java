package at.kaindorf.backend.mapper;

import at.kaindorf.backend.dto.AccountDTO;
import at.kaindorf.backend.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDTO toDTO(Account account);
    Account toEntity(AccountDTO accountDTO);
}
