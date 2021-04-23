import React from "react";
import moment from 'moment';
import styles from './Comment.scss';
import classNames from 'classnames/bind';
const cx = classNames.bind(styles);

const Comment = ({ comment }) => {
  const { userName, body, lastModifiedDate } = comment.toJS();

  return (
    <div>
      <div className="media mb-3">
        <img
          className="mr-3 bg-light rounded"
          width="48"
          height="48"
          src={`https://cours-informatique-gratuit.fr/wp-content/uploads/2014/05/compte-utilisateur-1.png`}
          alt={userName}
        />

        <div className={cx("comment-body")}>
          <small className="float-right text-muted">{moment(lastModifiedDate).format("lll")}</small>
          <h6 className="mt-0 mb-1 text-muted">{userName}</h6>
          {body}
        </div>
      </div>
    </div>
  );
}

export default Comment;