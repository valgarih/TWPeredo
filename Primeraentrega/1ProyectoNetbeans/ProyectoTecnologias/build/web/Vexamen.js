
const {
  Button,
  Icon,
  Typography,
  Paper,
  withStyles,
  Grid,
  ButtonBase,
  Checkbox,Input,FormControl,InputLabel,BottomNavigation,
  BottomNavigationAction
} = window['material-ui'];

const styles = theme => ({
    
  root: {
   textAlign: 'center',
    backgroundColor: '#FE6B8A',
    padding: theme.spacing.unit,
    paddingTop: theme.spacing.unit*6,
    paddingBottom: theme.spacing.unit *39,
    height: '100%',
    overflowX: 'auto'
  },margin: {
    margin: 0,

  },
  icon: {
    margin: theme.spacing.unit,
    fontSize: 32,
  },
  image: {
    position: 'center',
    maxHeight: 200,
    maxWidth: 500
  },
  main: {
    width: 'auto',
    display: 'block', // Fix IE 11 issue.
    marginLeft: theme.spacing.unit * 3,
    marginRight: theme.spacing.unit * 3,
    [theme.breakpoints.up(400 + theme.spacing.unit * 3 * 2)]: {
      width: 400,
      marginLeft: 'auto',
      marginRight: 'auto',
    },
  },
  paper: {
    padding: theme.spacing.unit * 20,
    paddingTop: theme.spacing.unit * 2,
    paddingBottom: theme.spacing.unit * 4,
    

 //   margin: 'auto',
  //  minWidth: 50,
    height: '100%',
    backgroundColor: '#e1bee7',
    overflowX: 'auto'
  },
  button: {
    margin: theme.spacing.unit,
  },
  submit: {
    marginTop: theme.spacing.unit * 3,
  },
  form: {
    width: '50%', // Fix IE 11 issue.
    marginTop: theme.spacing.unit,
  },
});

let numeroprueba=0;
function nextPage(){
    console.log(numeroprueba);
    if (numeroprueba === 0) {
        return numeroprueba
    }
    numeroprueba++;
    return numeroprueba;
}
class Index extends React.Component {
    
    constructor(props){
      super(props);
      this.state = {
          id: "",
          pregunta: "",
      //    respuestas:[],
          multimedia: "",
          tipo: "",
          codMulti: "",
          anterior: "",
          siguiente: ""
      }
    }
    
    tipodeMultimedia(tipo){
        const { classes } = this.props;
        console.log("Se llamó a la función, tipo es: "+tipo);
        if(tipo==="jpg" || tipo==="png" || tipo==="JPG"){
                return (<ButtonBase className={classes.image}> <img className={classes.img} src={this.state.multimedia} /> </ButtonBase>);
            }else if(tipo==="mp4" || tipo==="MP4" ){
                return (<video controls> <source src={this.state.multimedia} type='video/mp4' /> </video>);
            }else if(tipo==="mp3" || tipo==="MP3"){
                return (<audio controls> <source src={this.state.multimedia} type='audio/mpeg' /> </audio>);
            }
    }
    
    handleChange = name => event => {
    this.setState({ [name]: event.target.checked });
    }
    
    
    
    componentDidMount = () => {
      fetch("preguntamc.json", {
          method: "GET"
      }).then(response => response.json())
        .then( json =>{
            console.log(json);
            var x = json.multimedia.substring(json.multimedia.length -3, json.multimedia.length);
            console.log(x);
            this.setState ({
                id: json.id,
                pregunta: json.pregunta,
         //       respuestas: json.resp,
                multimedia: json.multimedia,
                tipo: x,
                anterior: json.anterior,
                siguiente: json.siguiente
      });return x;});
  }
  
  prueba(){
      console.log('hi', componentDidMount());
  }
  
  render() {
    const {classes} = this.props;
    return (
    <div>
       <Paper className={classes.root}> 
              <Paper className={classes.paper}>     

              
                <Typography gutterBottom variant="h6">{this.state.id}</Typography>
                <Typography variant="h5">{this.state.pregunta}</Typography>
                <FormControl margin="normal"  fullWidth>
                <InputLabel >Escriba su respuesta* </InputLabel>
                <Input id="resp1" name="resp1" /> 
                </FormControl>
                

          <Grid item justify="center">
              {this.tipodeMultimedia(this.state.tipo)}
          </Grid>
             
              
              
       <BottomNavigation
          onChange={this.handleChange}
          showLabels
          className={classes.paper}
        >
          <BottomNavigationAction  label={'Anterior: "'+this.state.anterior+'"' }  onClick = {() => window.location.replace("verExamen?pregunta="+this.state.anterior)} icon={ <Icon className={classes.icon}>skip_previous</Icon>  } />
          <BottomNavigationAction label={'Siguiente: "'+this.state.siguiente+'"'} onClick = {() => (window.location.replace("verExamen?pregunta="+this.state.siguiente))(prueba())} icon={<Icon className={classes.icon}>skip_next</Icon>} />
         </BottomNavigation>       
              
              
      </Paper>
      <a href="tableQuestions.html">
            <Button fullWidth variant="contained" color="secundary">Regresar</Button>
      </a>
       </Paper>
      </div>
    );
  }
}
const VerExamen = withStyles(styles)(Index);
ReactDOM.render(<VerExamen />, document.getElementById('root'));
