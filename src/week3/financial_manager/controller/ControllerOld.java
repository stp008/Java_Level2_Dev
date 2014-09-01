/**
 * @author clack008@gmail.com
 */

package week3.financial_manager.controller;

import java.util.Scanner;
import java.util.Set;

import week1.financial_manager.commands.Commands;
import week1.financial_manager.model.Model;
import week1.financial_manager.model.Record;
import week1.financial_manager.view.ViewImpl;

public class ControllerOld {
	Model model;
	ViewImpl view;

	public ControllerOld(Model model, ViewImpl view) {
		this.model = model;
		this.view = view;
	}

	public void init() {
		view.welcome();
	}

	public void process() {
		try (Scanner input = new Scanner(System.in)) {
			while (true) {
				view.enterCommand();
				String command = input.nextLine();
				String[] commands = command.split(" ");
				Commands enum_command;

				try {
					enum_command = Commands.valueOf(commands[0]);
				} catch (Exception e) {
					enum_command = Commands.BLANK;
				}

				try {
					switch (enum_command) {
					case HELP:
						view.help();
						break;
					case CREATE_USER:
						model.createUser(commands[1], commands[2]);
						view.createUser(commands[1], commands[2]);
						break;

					case DELETE_USER:
						model.removeMe();
						view.removeMe();
						break;

					case CREATE_ACCOUNT:
						if (model.createAccount(commands[1])) {
							view.createAccount(commands[1]);
						} else {
							view.notAuthorized();
						}
						break;

					case LOGIN:
						if (model.login(commands[1], commands[2])) {
							view.login(commands[1], commands[2]);
						} else {
							view.incorrectLogin();
						}
						break;

					case PUT:
						if (model.put(
								model.getAccount(Integer.valueOf(commands[1])),
								Double.valueOf(commands[2]))) {
							view.put(
									Integer.valueOf(commands[1]),
									Double.valueOf(commands[2]),
									model.getAccount(
											Integer.valueOf(commands[1]))
											.getBalance());
						} else {
							view.notAuthorized();
						}
						break;

					case WITHDRAW:
						if (model.withdraw(
								model.getAccount(Integer.valueOf(commands[1])),
								Double.valueOf(commands[2]))) {
							view.withdraw(
									Integer.valueOf(commands[1]),
									Double.valueOf(commands[2]),
									model.getAccount(
											Integer.valueOf(commands[1]))
											.getBalance());
						} else {
							view.notAuthorized();
						}
						break;

					case GET_HISTORY:
						Set<Record> records = model.getAccountRecords(model
								.getAccount(Integer.valueOf(commands[1])));
						if (records != null) {
							view.printRecords(records);
						} else {
							view.wrongAccount();
							System.out.println("or");
							view.notAuthorized();
						}
						break;

					case GET_ACCOUNTS:
						if (model.getUserAccounts() != null) {
							view.printAccounts(model.getUserAccounts());
						} else {
							view.notAuthorized();
						}
						break;

					case EXIT:
						System.exit(0);
						break;
					case BLANK:
						view.wrongCommand();
						break;
					}

				} catch (Exception e) {
					view.wrongArguments();
				}
			}
		}
	}
}
