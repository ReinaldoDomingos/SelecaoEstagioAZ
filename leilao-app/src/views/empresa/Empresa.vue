<template>
    <v-app>
        <v-content>
            <v-container fluid id="v-container">
                <v-form id="v-form" ref="form" lazy-validation>
                    <v-card-title id="">Cadastrar uma Empresa</v-card-title>
                    <v-form-base :value="empresa" :schema="empresaEsquema"/>
                    <v-btn class="primary" @click="validate">Salvar</v-btn>
                    <v-btn id="v-btn-resetar" @click="resetValidation">Resetar</v-btn>
                    <v-alert v-show="mostrarAlerta" class="text-danger">{{alerta}}</v-alert>
                </v-form>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import VFormBase from 'vuetify-form-base';
    import axios from "axios";

    const minLen = l => v => (v && v.length >= l) || `min. ${l} caracteres`
    const maxLen = l => v => (v && v.length <= l) || `max. ${l} caracteres`
    const validEmail = msg => v => /.+@.+\..+/.test(v) || msg;
    const validURL = msg => v => verificarURL(msg, v) || msg;
    const baseURLServidor = "http://localhost:8081"
    const baseURL = 'http://localhost:8080'

    function verificarURL(str, v) {
        if (!v) return true;//Campo não preenchido e não obrigatório
        var pattern = new RegExp('^(https?:\\/\\/)?' + // protocol
            '((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.)+[a-z]{2,}|' + // domain name
            '((\\d{1,3}\\.){3}\\d{1,3}))' + // OR ip (v4) address
            '(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*' + // port and path
            '(\\?[;&a-z\\d%_.~+=-]*)?' + // query string
            '(\\#[-a-z\\d_]*)?$', 'i'); // fragment locator
        return !!pattern.test(v);
    }

    // const validURL2 = msg => v => /https+.\/\/.+\..+/.test(v) || msg;
    const required = msg => v => !!v || `O campo ${msg} é Obrigatório`;

    export default {
        name: 'Empresa',
        components: {VFormBase},
        props: ['id'],
        data() {

            return {
                mostrarAlerta: false,
                alerta: 'Prencha todos campos obrigatórios com valores válidos!',
                empresa: {},
                empresaEsquema: {
                    razaoSocial: {
                        type: 'text', label: 'Razão Social',
                        rules: [required('Razão Social'), minLen(6), maxLen(64),],
                        flex: {xs: 12, lg: 3}
                    },
                    cnpj: {
                        type: 'text',
                        label: 'CNPJ',
                        mask: "##.###.###/####-##",
                        rules: [required("CNPJ"), minLen(14), maxLen(18)],
                        flex: {xs: 12, lg: 3},
                    },
                    municipio: {
                        type: 'text', label: 'Municipio', flex: {xs: 12, lg: 6},
                    },
                    logradouro: {
                        type: 'text', label: 'Logradouro',
                        flex: {xs: 12, lg: 3}
                    },
                    complemento: {type: 'text', label: 'Complemento', flex: {xs: 12, lg: 3},},
                    bairro: {type: 'text', label: 'Bairro', flex: {xs: 12, lg: 4}},
                    numero: {
                        type: 'number', label: 'Numero',
                        flex: {xs: 12, lg: 2}
                    },
                    cep: {type: 'text', label: 'CEP', flex: {xs: 12, lg: 3},},
                    telefone: {
                        type: 'text',
                        // mask: ['(##) ####-####', '(##) #####-####'],
                        label: 'Telefone',
                        flex: {xs: 12, lg: 3}
                    },
                    email: {
                        type: 'text',
                        label: 'Email',
                        rules: [required('email'), validEmail('Email inválido')],
                        flex: {xs: 12, lg: 6},
                    },
                    site: {type: 'url', label: 'Site', flex: {xs: 12, lg: 4}, rules: [validURL('URL Invaliddo')]},
                    usuario: {
                        type: 'text',
                        label: 'Usuário',
                        flex: {xs: 12, lg: 4},
                        rules: [required("Usuário"), minLen(4), maxLen(20)]
                    },
                    senha: {type: 'password', label: 'Password', flex: {xs: 12, lg: 4}},
                }
            }
        },
        mounted() {
            if (this.id) {
                let api = baseURLServidor + '/empresa/' + this.id;
                axios.get(api, {
                    headers: {
                        "Access-Control-Allow-Origin": "*",
                        "Access-Control-Allow-Methods": "GET, POST, PATCH, PUT, DELETE, OPTIONS",
                        "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
                        'Content-Type': 'application/json',
                    },
                }).then(res => {
                    this.empresa = res.data;
                }).catch(err => {
                    console.log(err.response);
                });
            }
        },
        methods: {
            validate() {
                if (this.$refs.form.validate()) {
                    this.mostrarAlerta = false;
                    let data = JSON.stringify(this.empresa);
                    if (this.id)
                        axios.put(baseURLServidor + "/empresa/" + this.empresa.id, data, {
                            headers: {
                                "Access-Control-Allow-Origin": "*",
                                "Access-Control-Allow-Methods": "GET, POST, PATCH, PUT, DELETE, OPTIONS",
                                "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
                                'Content-Type': 'application/json',
                            },
                        }).then(location.href = baseURL + '/#/empresas')
                    else
                        axios.post(baseURLServidor + "/empresa/", data, {
                            headers: {
                                "Access-Control-Allow-Origin": "*",
                                "Access-Control-Allow-Methods": "GET, POST, PATCH, PUT, DELETE, OPTIONS",
                                "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
                                'Content-Type': 'application/json',
                            },
                        }).then(location.href = baseURL + '/#/empresas')
                } else {
                    this.mostrarAlerta = true;
                }
            },
            resetValidation() {
                this.$refs.form.resetValidation()
            }
        }
    }
</script>

<style>
    #v-btn-resetar {
        margin-left: 1rem;
    }

    #v-container {
        /*width: auto;*/
        text-align: center !important;
        /*margin: auto;*/
        /*background: black;*/
    }

    #v-form {
        background-color: whitesmoke;
        padding: 2rem
    }

    /* CSS Item --- set all items  */
    #form-base .item {
        padding: 1rem
    }

    /* CSS Type --- set all items with type */
    #form-base .type-switch {
        border-bottom: 3px solid #E23
    }

    #form-base .type-checkbox {
        background-color: #fdd
    }

    /* CSS Keys --- select key in object 'myValue.controls.slider' */
    #form-base .key-controls-slider {
        background-color: #fec
    }

    /*#form-base .item input:valid {*/
    /*    color: #afa;*/
    /*}*/

    #form-base .type-email input:invalid {
        background-color: #faa;
    }

    .text-danger {
        color: red !important;
        background: transparent !important;
    }
</style>