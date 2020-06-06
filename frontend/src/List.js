import * as React from "react";
import { Item } from "./Item";

export class List extends React.Component {
    contactToContactItem = piece => {
        const { title, first, last } = piece.name;
        const artist = `${title} ${first} ${last}`.trim();
        const song = piece.phone;
        // const key = contact.login.username;
        return <Item artist={artist} song={song} />;
    };

    render() {
        return (
            <ul>
                {this.props.contacts.map(this.contactToContactItem)}
            </ul>
        );
    }
}