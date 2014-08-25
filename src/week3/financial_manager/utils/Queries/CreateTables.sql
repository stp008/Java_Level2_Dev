CREATE TABLE IF NOT EXISTS [Users] (
  [id] INTEGER PRIMARY KEY ON CONFLICT ABORT AUTOINCREMENT, 
  [password] VARCHAR(20) NOT NULL ON CONFLICT ABORT, 
  [login] VARCHAR(20) NOT NULL ON CONFLICT ABORT, 
  [fname] VARCHAR(20), 
  [sname] VARCHAR(20), 
  [mname] VARCHAR(20));


CREATE TABLE IF NOT EXISTS [Accounts] (
  [id] INTEGER PRIMARY KEY ON CONFLICT ABORT AUTOINCREMENT, 
  [user_id] INTEGER(5) NOT NULL ON CONFLICT ABORT CONSTRAINT [user_id] REFERENCES [Users]([id]) ON DELETE RESTRICT ON UPDATE RESTRICT, 
  [balance] DOUBLE(15) NOT NULL ON CONFLICT ABORT, 
  [description] VARCHAR(100));

  
CREATE TABLE IF NOT EXISTS [Categories] (
  [id] INTEGER PRIMARY KEY ON CONFLICT ABORT AUTOINCREMENT, 
  [name] VARCHAR(15) NOT NULL ON CONFLICT ABORT, 
  [description] VARCHAR(100));


CREATE TABLE IF NOT EXISTS [Records] (
  [id] INTEGER PRIMARY KEY ON CONFLICT ABORT AUTOINCREMENT, 
  [account_id] INTEGER(5) NOT NULL ON CONFLICT ABORT CONSTRAINT [account_id] REFERENCES [Accounts]([id]) ON DELETE RESTRICT ON UPDATE RESTRICT, 
  [operation_type] INTEGER(1) NOT NULL ON CONFLICT ABORT, 
  [amount] DOUBLE(15) NOT NULL ON CONFLICT ABORT, 
  [record_date] DATE NOT NULL ON CONFLICT ABORT, 
  [description] VARCHAR(100), 
  [category_id] INTEGER(5) NOT NULL ON CONFLICT ABORT CONSTRAINT [category_id] REFERENCES [Categories]([id]) ON DELETE RESTRICT ON UPDATE RESTRICT);

CREATE INDEX IF NOT EXISTS [user_id] ON [Accounts] ([user_id]);

CREATE INDEX IF NOT EXISTS [account_id] ON [Records] ([account_id]);

CREATE INDEX IF NOT EXISTS [category_id] ON [Records] ([category_id]);

CREATE UNIQUE INDEX IF NOT EXISTS [password] ON [Users] ([password]);