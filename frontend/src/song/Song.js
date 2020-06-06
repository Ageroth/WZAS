import React, {Component} from "react";
import './Song.css'

export class Song extends Component {
    constructor(props) {
        super(props);
        this.state = {
            songId: props.songId,
            artist: props.artist,
            song: props.song,
            link: props.link,
            text: props.text
        }
    }

    render() {
        const songId = this.state.songId;
        const artist = this.state.artist;
        const song = this.state.song;
        const link = this.state.link;
        const text = this.state.text;


        return <article className="Post" ref="Post">
            <header>
                <div>
                    <div>
                        {artist}, {song}
                    </div>
                </div>
            </header>
            <div>
                <div>
                    {link}
                </div>
            </div>
            <div className="Post-description">
                <label>{text}</label>
            </div>
        </article>;
    }
}