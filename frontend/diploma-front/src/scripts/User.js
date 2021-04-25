export default class User {
  constructor(username) {
    this.username = username;
  }

  logIn(username, password) {
    this.username = username;
    this.password = password;
  }
}
