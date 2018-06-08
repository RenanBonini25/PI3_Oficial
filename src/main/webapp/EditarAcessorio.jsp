<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="PT-BR">
    <head>
        <meta charset="utf-8">
        <title>Jupiter - Edição de Acessórios</title>
        <link rel="stylesheet" type="text/css" href="_css/estilo.css">
        <script>
            function edicao()
            {
                alert("Acessório atualizado com sucesso!");
            }
        </script>
    </head>
    <body>
        <header>
            <a href="home.jsp"><p id="txtJupiter">JUPITER</p></a>
            <p id="usuario">Bem vindo, ${usuario.nome}</p>
        </header>
        <div id="menuContainer">
            <nav>
                <h2>Menu</h2>
                <ul id="menu">	
                    <a href="${pageContext.request.contextPath}/ListagemClientes"><li id="listaMenu"><img src="_imagens/cliente.png">Clientes</li></a>
                    <li id="listaSubMenu">
                        <img src="_imagens/produto.png">Produtos
                        <ul id="subMenuProdutos">
                            <a href="${pageContext.request.contextPath}/ListagemGames"><li id="itemSub"><img src="_imagens/games.png">Games</li></a>
                            <a href="${pageContext.request.contextPath}/ListagemConsoles"><li id="itemSub"><img src="_imagens/console.png">Consoles</li></a>
                            <a href="${pageContext.request.contextPath}/ListagemAcessorios"><li id="itemSub"><img src="_imagens/acessorio.png">Acessórios</li></a>
                            <a href="${pageContext.request.contextPath}/ListagemActionFigures"><li id="itemSub"><img src="_imagens/actionFigure.png">Action Figures</li></a>
                        </ul>
                    </li>
                    <a href="${pageContext.request.contextPath}/ListagemUsuarios"><li id="listaMenu"><img src="_imagens/funcionario.png">Usuários</li></a>
                    <a href="${pageContext.request.contextPath}/ListagemFiliais"><li id="listaMenu"><img src="_imagens/filial.png">Filiais</li></a>
                    <a href="Vendas_ProcurarCliente.jsp"><li id="listaMenu"><img src="_imagens/venda.png">Vendas</li></a>
                    <a href="${pageContext.request.contextPath}/GerarRelatorios.jsp"><li id="listaMenu"><img src="_imagens/relatorio.png">Relatório</li></a>
                    <a href="${pageContext.request.contextPath}/Logout"><li id="listaMenu"><img src="_imagens/power.png">Logout</li></a>
                </ul>
            </nav>		
        </div>
        <div id="corpo">
            <c:forEach items="${acessorio}" var="acessorio">
                <form action="${pageContext.request.contextPath}/EditarAcessorio" method="post" id="formularioCadastro">
                    <input type="hidden" value="${acessorio.id}" name="id">
                    <input type="text" name="txtNome" value="${acessorio.nome}" placeholder="NOME" class="formulario">
                    </br></br>
                    <input type="text" name="txtDescricao" value="${acessorio.descricao}" placeholder="DESCRIÇÃO" class="formulario">
                    </br></br>
                    <input type="text" name="txtPlataforma" value="${acessorio.plataforma}" placeholder="PLATAFORMA" class="formulario">
                    </br></br>
                    <input type="text" name="txtCor" value="${acessorio.cor}" placeholder="COR" class="formSelect">
                    <input type="number" name="txtQuantidade" value="${acessorio.quantidade}" placeholder="QUANTIDADE" class="formSelect">
                    </br></br>
                    <input type="text" name="txtPrecoCompra" value="${acessorio.precoCompra}" placeholder="PREÇO COMPRA" class="formSelect">
                    <input type="text" name="txtPrecoVenda" value="${acessorio.precoVenda}" placeholder="PREÇO VENDA" class="formSelect">
                    </br></br>
                    <input type="submit" value="SALVAR" id="botao" onclick=edicao()>
                </form>
            </c:forEach>
        </div>
    </body>
</html>