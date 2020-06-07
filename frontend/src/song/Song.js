import React, {Component} from "react";
import './Song.css'
import styles from './Song.css';

export class Song extends Component {
    constructor(props) {
        super(props);
        this.state = {
            songId: props.songId,
            artist: props.artist,
            song: props.song,
            link: props.link,
            text: props.text,
            word: props.word
        }
    }




    render() {
        const songId = this.state.songId;
        const artist = this.state.artist;
        const song = this.state.song;
        const word = this.state.word;
        const text = this.state.text;


        return (

            <div id="song">
                <div className='list-group mb-4'>
                    <div id="artist">
                        Artysta: {artist}
                    </div>
                    <div id="name" >
                        Piosenka: {song}
                    </div>
                    <div id="text">
                        {text}
                    </div>

                </div>
            </div>

        );
    }

}