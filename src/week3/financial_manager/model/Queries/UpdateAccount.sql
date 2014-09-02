/**
 * @author clack008@gmail.com
 */

UPDATE Accounts SET balance = (SELECT balance FROM Accounts WHERE id = ?) + ? WHERE id = ?