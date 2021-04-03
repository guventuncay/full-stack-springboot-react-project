import React, { Component } from "react";
import bootstrap from "bootstrap/dist/css/bootstrap.css";
import $ from "jquery";

export default class Book extends Component {
  state = {
    comments: [],
    newComment: "",
    loading: true,
    // comment - shallow merge
    id: null,
    text: "",
    user: null,
    book: this.props.book,
  };

  async componentDidMount() {
    await this.getComments();
  }

  updateCommentText = (event) => {
    this.setState({ text: event.target.value });
  };

  save = (event) => {
    event.preventDefault();
    const text = this.state.text;
    if (!text) return;
    const book = this.state.book;
    const user = {
      id: 1,
    };

    $.ajax({
      url: "http://localhost:8080/api/v1/comments",
      method: "post",
      dataType: "json",
      contentType: "application/json",
      data: JSON.stringify({
        text: text,
        book: book,
        user: user,
      }),
      complete: this.commentPostComplated(text),
    });
  };

  commentPostComplated(text) {
    this.getComments();
    this.setState({
      newComment: "Güven: " + text,
      text: "",
    });
  }

  async getComments() {
    const url = "http://localhost:8080/api/v1/comments";
    const response = await fetch(url);
    const comments = await response.json();
    this.setState({ comments, loading: true });
  }

  render() {
    if (!this.state.comments)
      return <div>There is no comment for this book yet.</div>;

    if (!this.state.loading) return <div>Loading comments...</div>;

    return (
      <div>
        <div>
          <img
            src={"./images/book-" + this.props.book.id + ".png"}
            width="150"
          />
          <div>Book Name: {this.props.book.name}</div>
          <div>Author: {this.props.book.author}</div>
          <div>Page Count: {this.props.book.pageCount}</div>
          <div>Release Date: {this.props.book.releaseDate}</div>
        </div>
        <hr />
        {this.showComments()}
        {/* sync bug */}
        {this.state.newComment}
        <form onSubmit={this.save}>
          Write comment:
          <input
            name="text"
            value={this.state.text}
            onChange={this.updateCommentText}
          />
          <button type="submit">Submit</button>
        </form>
        <hr />
        <button
          className="btn btn-success"
          onClick={this.props.handleBackToMain}
        >
          go back to main menu
        </button>
      </div>
    );
  }

  showComments() {
    return (
      <div>
        Comments:
        {this.state.comments
          .filter((comment) => comment.book.id === this.props.book.id)
          .map((comment) => (
            <div
              key={comment.id}
              className="col"
              onClick={() => this.setState({ bookToShow: comment.id })}
            >
              <div>
                {comment.user.firstName}: {comment.text}
              </div>
            </div>
          ))}
      </div>
    );
  }
}
