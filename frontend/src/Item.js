import * as React from "react";

export const Item = ({artist, song }) => {
    return (
        <li className="item">
            <div className="content">
                <h4 className="header">{artist}</h4>
                <div className="description">{song}</div>
            </div>
        </li>
    );
};