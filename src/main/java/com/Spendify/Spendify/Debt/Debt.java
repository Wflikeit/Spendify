package com.Spendify.Spendify.Debt;

import com.Spendify.Spendify.Expense.Expense;
import com.Spendify.Spendify.User.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Debt {
    @Id
    @SequenceGenerator(name = "debt_sequence",
            sequenceName = "debt_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "debt_sequence"
    )
    private Long debtId;
    @OneToMany(mappedBy = "debt")
    private List<Expense> expenses;
    @Column(nullable = false)
    private Date date;
    @ManyToMany
    private Set<User> users;
}
