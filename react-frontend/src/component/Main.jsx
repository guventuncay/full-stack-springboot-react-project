import React, { Component } from "react";
import bootstrap from "bootstrap/dist/css/bootstrap.css";
import Book from "./Book";

export default class Main extends Component {
  state = {
    books: [],
    loading: true,
    bookToShow: null,
  };

  async componentDidMount() {
    const url = "http://localhost:8080/api/v1/books";
    const response = await fetch(url);
    const books = await response.json();
    this.setState({ books, loading: false });
  }

  handleBackToMain = () => {
    this.setState({ bookToShow: null });
  };

  render() {
    if (this.state.loading) return <div>Loading books...</div>;

    if (!this.state.books) return <div>No books found...</div>;

    if (this.state.bookToShow)
      return (
        <div>
          <Book
            handleBackToMain={this.handleBackToMain}
            book={this.state.books[this.state.bookToShow - 1]}
          />
        </div>
      );

    return (
      <div>
        <div className="container">
          <div className="row">
            {this.state.books.map((book) => (
              <div
                key={book.id}
                className="col"
                onClick={() => this.setState({ bookToShow: book.id })}
              >
                <img src={"./images/book-" + book.id + ".png"} width="150" />
                <div>{book.id}</div>
                <div>{book.name}</div>
                <div>{book.author}</div>
              </div>
            ))}
          </div>
        </div>
      </div>
    );
  }
}
