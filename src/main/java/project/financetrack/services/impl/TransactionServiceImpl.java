package project.financetrack.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.financetrack.dtos.transaction.TransactionDTOPost;
import project.financetrack.entities.MaturityEntity;
import project.financetrack.entities.TransactionEntity;
import project.financetrack.repositories.GenericRepository;
import project.financetrack.repositories.MaturityRepository;
import project.financetrack.repositories.TransactionRepository;
import project.financetrack.repositories.specs.SpecificationBuilder;
import project.financetrack.services.CategoryService;
import project.financetrack.services.TransactionService;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final ModelMapper modelMapper;

    private final TransactionRepository transactionRepository;

    private final CategoryService categoryService;

    private final SpecificationBuilder<TransactionEntity> specificationBuilder;

    @Override
    public TransactionEntity create(TransactionDTOPost dtoPost) {
        TransactionEntity transaction = TransactionEntity.builder()
                .category(categoryService.getById(dtoPost.getCategoryId()))
                .quantity(dtoPost.getQuantity())
                .build();

        return TransactionService.super.createWithEntity(transaction);
    }

    @Override
    public ModelMapper getMapper() {
        return modelMapper;
    }

    @Override
    public GenericRepository<TransactionEntity, Long> getRepository() {
        return transactionRepository;
    }

    @Override
    public SpecificationBuilder<TransactionEntity> specificationBuilder() {
        return specificationBuilder;
    }

    @Override
    public Class<TransactionEntity> entityClass() {
        return TransactionEntity.class;
    }

    @Override
    public Class<TransactionEntity> modelClass() {
        return TransactionEntity.class;
    }
}
