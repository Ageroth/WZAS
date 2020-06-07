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

    highlightText(sentence, wordToHighlight){

        let highlightedText = sentence.split(" ")
            .map(word => word.toUpperCase() === wordToHighlight.toUpperCase() ? `<b>${word}</b>` : word)
            .join(" ");

        return  {__html: highlightedText};
    }


    render() {
        const songId = this.state.songId;
        const artist = this.state.artist;
        const song = this.state.song;
        const word = this.state.word;
        const text = this.state.text;


        // return <article className="Post" ref="Post">
        //     <header>
        //         <div>
        //             <div>
        //                 <h1>
        //                     {artist}
        //                 </h1>
        //                <h1>
        //                    {song}
        //                </h1>
        //
        //             </div>
        //         </div>
        //     </header>
        //     <div>
        //         <div>
        //             <h1>
        //                 {link}
        //             </h1>
        //
        //         </div>
        //     </div>
        //     <div className="Post-description">
        //         <label>{text}</label>
        //     </div>
        // </article>;

        return (
            // <div className={styles.app}>
            //
            //     <table className={styles.table}>
            //         <thead>
            //         <tr>
            //             <th>Artysta</th>
            //             <th>Piosenka</th>
            //             <th>Tekst</th>
            //         </tr>
            //         </thead>
            //         <tbody>
            //         <tr>
            //             <td>{artist}</td>
            //             <td>{song}</td>
            //             <td>{text}</td>
            //         </tr>
            //
            //         </tbody>
            //     </table>
            //
            //
            //
            //
            // </div>
            <div id="song">
                <div className='list-group mb-4'>
                    <div id="artist">
                        Artysta: {artist}
                    </div>
                    <div id="name" >
                        Piosenka: {song}
                    </div>
                    {/*<div id="text">*/}
                    {/*    {text}*/}
                    {/*</div>*/}
                    <div id="text" dangerouslySetInnerHTML={this.highlightText(text, "vfjvfjh")} />
                </div>
            </div>

        );
    }

}